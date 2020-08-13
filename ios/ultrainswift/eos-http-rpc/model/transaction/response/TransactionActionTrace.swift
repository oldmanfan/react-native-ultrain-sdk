import Foundation

public struct TransactionActionTrace : Codable {
    public let receipt: TransactionReceipt
    public let act: TransactionAct
    public let elapsed: Int
    public let cpu_usage: Int?
    public let console: String
    public let total_cpu_usage: Int?
    public let trx_id: String
    public let return_value: String
    public let inline_traces: Array<TransactionActionTrace>

    public init(receipt: TransactionReceipt, act: TransactionAct, elapsed: Int, cpu_usage: Int, console: String, total_cpu_usage: Int, trx_id: String, return_value: String, inline_traces: Array<TransactionActionTrace>) {
        self.receipt = receipt
        self.act = act
        self.elapsed = elapsed
        self.cpu_usage = cpu_usage
        self.console = console
        self.total_cpu_usage = total_cpu_usage
        self.trx_id = trx_id
        self.return_value = return_value
        self.inline_traces = inline_traces
    }

    enum CodingKeys: String, CodingKey {
        case receipt
        case act
        case elapsed
        case cpu_usage
        case console
        case total_cpu_usage
        case trx_id
        case return_value
        case inline_traces
    }

    public func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(receipt, forKey: .receipt)
        try container.encode(act, forKey: .act)
        try container.encode(elapsed, forKey: .elapsed)
        try container.encode(cpu_usage, forKey: .cpu_usage)
        try container.encode(console, forKey: .console)
        try container.encode(total_cpu_usage, forKey: .total_cpu_usage)
        try container.encode(trx_id, forKey: .trx_id)
        try container.encode(return_value, forKey: .return_value)
        try container.encode(inline_traces, forKey: .inline_traces)
    }
}
