import Foundation
import RxSwift

public class BuyRamBytesChain : ChainTransaction {

    public struct Args {
        public let receiver: String
        public let quantity: Int64

        public init(receiver: String, quantity: Int64) {
            self.receiver = receiver
            self.quantity = quantity
        }
    }

    private let _chainApi: ChainApi

    public init(chainApi: ChainApi) {
        self._chainApi = chainApi
    }

    public func chainApi() -> ChainApi {
        return _chainApi
    }

    public func buyRamBytes(args: Args, transactionContext: TransactionContext) -> Single<ChainResponse<TransactionCommitted>> {
        return push(
            actions: buildAbiList(args: args, transactionContext: transactionContext),
            transactionContext: transactionContext
        )
    }

    private func buildAbiList(args: Args, transactionContext: TransactionContext) -> [ActionAbi] {

        let buyRamBytesArgs: BuyRamBytesArgs = BuyRamBytesArgs(
            payer: AccountNameWriterValue(name: transactionContext.authorizingAccountName),
            receiver: AccountNameWriterValue(name: args.receiver),
            bytes: 4096)

        return [ActionAbi(
            account: AccountNameWriterValue(name: "eosio"),
            name: AccountActionWriterValue(name: "buyrambytes"),
            authorization: [TransactionAuthorizationAbi(
                actor: AccountNameWriterValue(name: transactionContext.authorizingAccountName),
                permission: AccountNameWriterValue(name: "active"))],
            data: DataWriterValue(hex: BuyRamBytesBody(args: buyRamBytesArgs).toHex(transactionContext.abiEncoder()))
        )]
    }
}
