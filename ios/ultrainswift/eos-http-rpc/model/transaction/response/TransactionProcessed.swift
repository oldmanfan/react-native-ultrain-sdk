import Foundation

public struct TransactionProcessed : Codable {
    public let id: String
    public let receipt: TransactionParentReceipt
    public let elapsed: Int
    public let net_usage: Int
    public let scheduled: Bool
    public let action_traces: Array<TransactionActionTrace>
    public let except: String?
    public let ability: String

    public init(id: String, receipt: TransactionParentReceipt, elapsed: Int, net_usage: Int, scheduled: Bool, action_traces: Array<TransactionActionTrace>, except: String? = nil, ability: String) {
        self.id = id
        self.receipt = receipt
        self.elapsed = elapsed
        self.net_usage = net_usage
        self.scheduled = scheduled
        self.action_traces = action_traces
        self.except = except
        self.ability = ability
    }

    enum CodingKeys: String, CodingKey {
        case id
        case receipt
        case elapsed
        case net_usage
        case scheduled
        case action_traces
        case except
        case ability
    }

    public func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(id, forKey: .id)
        try container.encode(receipt, forKey: .receipt)
        try container.encode(elapsed, forKey: .elapsed)
        try container.encode(net_usage, forKey: .net_usage)
        try container.encode(scheduled, forKey: .scheduled)
        try container.encode(action_traces, forKey: .action_traces)
        try container.encode(except, forKey: .except)
        try container.encode(ability, forKey: .ability)
    }
}
