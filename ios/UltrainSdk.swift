import Foundation
//import eosswift
import BigInt
import RxSwift
import CommonCrypto

struct RNResponse: Codable {
  var isSuccess: Bool
  var message: String
  var data: String
}

func successResolve<T: Codable>(obj: T) throws -> String {
//  let s = try JSONSerialization.data(withJSONObject: obj)
  let encode = JSONEncoder()
  let s = try encode.encode(obj)
  let result = String(data: s, encoding: .utf8)!

  let rns = RNResponse(isSuccess: true, message: "success", data: result)
  let enc2 = JSONEncoder()
  let s2 = try enc2.encode(rns)
//  let s2 = try JSONSerialization.data(withJSONObject: rns)
  return String(data: s2, encoding: .utf8)!
}

@objc(UltrainSdkModule)
class ChainApiModule: NSObject {

  @objc
  func sha256(_ rawStr: String, resolver resolve: RCTPromiseResolveBlock, rejector reject: RCTPromiseRejectBlock) -> Void {
    let data = rawStr.data(using: .utf8)!
    var hash = [UInt8](repeating: 0,  count: Int(CC_SHA256_DIGEST_LENGTH))
    data.withUnsafeBytes {
        _ = CC_SHA256($0, CC_LONG(data.count), &hash)
    }
    resolve(Data(hash).hexEncodedString())
  }


//  @objc(getPublicKey:success:reject:)
  @objc
  func getPublicKey(_ privateKey: String, resolver resolve: RCTPromiseResolveBlock, rejector reject: RCTPromiseRejectBlock) -> Void {
    do {
      let priv = try EOSPrivateKey(base58: privateKey);
      resolve(priv.publicKey.base58)
    } catch {
      reject("Error: ", "", error)
    }
  }

  private func compositeRsaKeys(mnemonics: [String], resolver resolve: RCTPromiseResolveBlock, rejector reject: RCTPromiseRejectBlock) -> Void {
    do {
      let mne = try Mnemonic(phrase: mnemonics)
      let hexSeed = mne.seed.hexa

      let ha = hexSeed.data(using: .ascii)!
      let privData = Sha256Hash.hash(data: ha)

      let eosPrivateKey = EOSPrivateKey(ecKeyPrivateKey: ECPrivateKey(privKeyData: privData))
      var rsa = [String: String]()
      rsa["mnemonics"] = mnemonics.joined(separator: " ")
      rsa["privateKey"] = eosPrivateKey.base58
      rsa["publicKey"] = eosPrivateKey.publicKey.base58
      resolve(rsa)
    } catch {
      reject("Error: ", error.localizedDescription, error)
    }
  }

  @objc
  func generatePrivate(_ resolve: RCTPromiseResolveBlock, rejector reject: RCTPromiseRejectBlock) -> Void {
    let random = Mnemonic()
    let mnemonics = random.phrase
    self.compositeRsaKeys(mnemonics: mnemonics, resolver: resolve, rejector: reject)
  }

  @objc
  func seedPrivate(_ mnemonicStr: String, resolver resolve: RCTPromiseResolveBlock, rejector reject: RCTPromiseRejectBlock) -> Void {
    let mnemonics = mnemonicStr.components(separatedBy: " ")
    self.compositeRsaKeys(mnemonics: mnemonics, resolver: resolve, rejector: reject)
  }

  @objc
  func signTx(_ url: String, txInfo: NSDictionary, resolver resolve: @escaping RCTPromiseResolveBlock, rejector reject: @escaping RCTPromiseRejectBlock) -> Void {
    let chainApi = ChainApiFactory.create(rootUrl:url)

    let contract = txInfo["contract"] as! String
    let action = txInfo["action"] as! String
    let account = txInfo["account"] as! String
    let privateKey = txInfo["privateKey"] as! String
    let pl = txInfo["permissionLevel"] as! String
    let paramsObj = txInfo["params"] as! [String: Any]

    _ = chainApi.abiJsonToBin(body: AbiJsonToBin(code: contract, action: action, args: paramsObj)).subscribe(
      onSuccess: { response in
          do {
            let binHex: AbiBinargs = response.body!
            let context = TransactionContext(authorizingAccountName: account, authorizingPrivateKey: try EOSPrivateKey(base58: privateKey), expirationDate: Date.defaultTransactionExpiry())
            _ = CommonActionChain(chainApi: chainApi).sign(contract: contract, action: action, hexData: binHex.binargs, pemissionLevel: pl, transactionContext: context).subscribe(
              onSuccess: { signature in
                resolve(signature)
            },
            onError: {error in
              reject("Error: ", error.localizedDescription, error)
            }) // end of CommonActionChain.sign
          } catch {
            reject("Error: ", error.localizedDescription, error)
          }
     },
    onError: { error in
          reject("Error: ", error.localizedDescription, error)
    })
  }

  @objc
  func getAccount(_ url: String, account: String, resolver resolve: @escaping RCTPromiseResolveBlock, rejector reject: @escaping RCTPromiseRejectBlock) -> Void {
    let chainApi = ChainApiFactory.create(rootUrl:url)
    _ = chainApi.getAccount(body: AccountName(account_name: account)).subscribe(onSuccess: { response in
      do {
        let info: Account = response.body!
        let str = try successResolve(obj: info)
        resolve(str)
      } catch {
        reject("Error: ", error.localizedDescription, error);
      }
    }, onError: { error in
      reject("Error: ", error.localizedDescription, error)
    })
  }

  @objc
  func getChainInfo(_ url: String, resolver resolve: @escaping RCTPromiseResolveBlock, rejector reject: @escaping RCTPromiseRejectBlock) -> Void {
    let chainApi = ChainApiFactory.create(rootUrl:url)
    _ = chainApi.getInfo().subscribe(onSuccess: { response in
      do {
        let info: Info = response.body!
        let str = try successResolve(obj: info)
        resolve(str)
      } catch {
        reject("Error: ", error.localizedDescription, error);
      }
    }, onError: { error in
      reject("Error: ", error.localizedDescription, error)
    })
  }

  @objc
  func getCurrencyBalance(_ url: String, params: NSDictionary, resolver resolve: @escaping RCTPromiseResolveBlock, rejector reject: @escaping RCTPromiseRejectBlock) -> Void {
    let chainApi = ChainApiFactory.create(rootUrl:url)

    let account = params["account"] as! String;
    let contract = params["contract"] as! String;
    let symbol = params["symbol"] as! String;
    let gcb = GetCurrencyBalance(code: contract, account: account, symbol: symbol)
    _ = chainApi.getCurrencyBalance(body: gcb).subscribe(onSuccess: { response in
      do {
        let balance: Array<String> = response.body!
        let str = try successResolve(obj: balance)
        resolve(str)
      } catch {
        reject("Error: ", error.localizedDescription, error);
      }
    }, onError: { error in
      reject("Error: ", error.localizedDescription, error)
    })
  }

  @objc
  func getTransferFee(_ url: String, blockHeight: UInt64, resolver resolve: @escaping RCTPromiseResolveBlock, rejector reject: @escaping RCTPromiseRejectBlock) -> Void {
    let chainApi = ChainApiFactory.create(rootUrl:url)
    let bh = BlockHeight(block_height: blockHeight)
    _ = chainApi.getTransferFee(body: bh).subscribe(onSuccess: { response in
      do {
        let fee: TransferFee = response.body!
        let str = try successResolve(obj: fee)
        resolve(str)
      } catch {
        reject("Error: ", error.localizedDescription, error);
      }
    }, onError: { error in
      reject("Error: ", error.localizedDescription, error)
    })
  }

  @objc
  func pushTx(_ url: String, txInfo: NSDictionary, resolver resolve: @escaping RCTPromiseResolveBlock, rejector reject: @escaping RCTPromiseRejectBlock) -> Void {
    let chainApi = ChainApiFactory.create(rootUrl:url)

    let contract = txInfo["contract"] as! String
    let action = txInfo["action"] as! String
    let account = txInfo["account"] as! String
    let privateKey = txInfo["privateKey"] as! String
    let pl = txInfo["permissionLevel"] as! String
    let paramsObj = txInfo["params"] as! [String: Any]

    _ = chainApi.abiJsonToBin(body: AbiJsonToBin(code: contract, action: action, args: paramsObj)).subscribe(
      onSuccess: { response in
          do {
            let binHex: AbiBinargs = response.body!
            let context = TransactionContext(authorizingAccountName: account, authorizingPrivateKey: try EOSPrivateKey(base58: privateKey), expirationDate: Date.defaultTransactionExpiry())
            _ = CommonActionChain(chainApi: chainApi).exec(contract: contract, action: action, hexData: binHex.binargs, pemissionLevel: pl, transactionContext: context).subscribe(
              onSuccess: { response in
                do {
                  let commit: TransactionCommitted = response.body!
                  let str = try successResolve(obj: commit)
                  resolve(str)
                } catch {
                  reject("Error: ", error.localizedDescription, error)
                }
            },
            onError: {error in
              reject("Error: ", error.localizedDescription, error)
            }) // end of CommonActionChain.exec
          } catch {
            reject("Error: ", error.localizedDescription, error)
          }
     },
    onError: { error in
          reject("Error: ", error.localizedDescription, error)
    })
  }

  @objc
  func transfer(_ url: String, params: NSDictionary, resolver resolve: @escaping RCTPromiseResolveBlock, rejector reject: @escaping RCTPromiseRejectBlock) -> Void {
    let chainApi = ChainApiFactory.create(rootUrl:url)
    let from = params["from"] as! String
    let to = params["to"] as! String
    let quantity = params["quantity"] as! String
    let memo = params["memo"] as! String

    let actor = params["actor"] as! String
    let privateKey = params["privateKey"] as! String
    let pk = try! EOSPrivateKey(base58: privateKey)

    let args = TransferChain.Args(fromAccount: from, toAccount: to, quantity: quantity, memo: memo)
    let context = TransactionContext(authorizingAccountName: actor, authorizingPrivateKey: pk, expirationDate: Date.defaultTransactionExpiry())

    _ = TransferChain(chainApi: chainApi).transfer(contract: "utrio.token", args: args, transactionContext: context).subscribe(
      onSuccess: { response in
        do {
          let commit: TransactionCommitted = response.body!
          let str = try successResolve(obj: commit)
          resolve(str)
        } catch {
          reject("Error: ", error.localizedDescription, error)
        }
      },
      onError: { error in
        reject("Error: ", error.localizedDescription, error)
    })
  }

  @objc
  static func requiresMainQueueSetup() -> Bool {
    return false
  }
//  @objc
//  func constantsToExport() -> [String: Any]! {
//    return ["someKey": "someValue"]
//  }

}

extension Data {
    struct HexEncodingOptions: OptionSet {
        let rawValue: Int
        static let upperCase = HexEncodingOptions(rawValue: 1 << 0)
    }

    func hexEncodedString(options: HexEncodingOptions = []) -> String {
        let format = options.contains(.upperCase) ? "%02hhX" : "%02hhx"
        return map { String(format: format, $0) }.joined()
    }
}
