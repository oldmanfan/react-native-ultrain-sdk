import Foundation

public struct TransactionCommitted : Codable {
    public let transaction_id: String
    public let processed: TransactionProcessed

    public init(transaction_id: String, processed: TransactionProcessed) {
        self.transaction_id = transaction_id
        self.processed = processed
    }

    enum CodingKeys: String, CodingKey {
        case transaction_id
        case processed
    }

    public func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(transaction_id, forKey: .transaction_id)
        try container.encode(processed, forKey: .processed)
    }
}
