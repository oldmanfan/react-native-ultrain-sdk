import Foundation

public struct Info : Codable {
    public let server_version: String
    public let chain_id: String
    public let head_block_num: Int
    public let last_irreversible_block_num: Int
    public let last_irreversible_block_id: String
    public let head_block_id: String
    public let head_block_time: String
    public let head_block_proposer: String
    public let virtual_block_cpu_limit: Int
    public let virtual_block_net_limit: Int
    public let block_cpu_limit: Int
    public let block_net_limit: Int
    public let block_interval_ms: Int

    public init(server_version: String, chain_id: String, head_block_num: Int, last_irreversible_block_num: Int, last_irreversible_block_id: String, head_block_id: String, head_block_time: String, head_block_producer: String) {
        self.server_version = server_version
        self.chain_id = chain_id
        self.head_block_num = head_block_num
        self.last_irreversible_block_num = last_irreversible_block_num
        self.last_irreversible_block_id = last_irreversible_block_id
        self.head_block_id = head_block_id
        self.head_block_time = head_block_time
        self.head_block_proposer = head_block_producer
        
        self.virtual_block_cpu_limit = 0
        self.virtual_block_net_limit = 0
        self.block_cpu_limit = 0
        self.block_net_limit = 0
        self.block_interval_ms = 0
    }
}
