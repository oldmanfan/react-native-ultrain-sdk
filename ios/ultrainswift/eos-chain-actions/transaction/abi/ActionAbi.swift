import Foundation

public struct ActionAbi : Encodable {
    public let account: AccountNameWriterValue
    public let name: AccountActionWriterValue
    public let authorization: [TransactionAuthorizationAbi]
    public let data: DataWriterValue

    public init(account: AccountNameWriterValue, name: AccountActionWriterValue, authorization: [TransactionAuthorizationAbi], data: DataWriterValue) {
        self.account = account
        self.name = name
        self.authorization = authorization
        self.data = data
    }
}
