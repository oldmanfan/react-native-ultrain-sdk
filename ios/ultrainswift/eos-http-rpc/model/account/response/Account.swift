import Foundation

public struct Account : Codable {
    public let account_name: String
    public let head_block_num: Int
    public let head_block_time: Date
    public let empowerchains: Array<String>
    public let privileged: Bool
    public let updateable: Bool
    public let last_code_update: Date
    public let created: Date
    public let core_liquid_balance: String?
    public let ram_quota: Int64
    public let net_weight: Int64
    public let cpu_weight: Int64
    public let net_limit: AccountResourceLimit
    public let cpu_limit: AccountResourceLimit
    public let ram_usage: Int64
    public let permissions: Array<AccountPermission>
    public let chain_resource: Array<ChainResource>
    public let self_delegated_consensus: String?
    public let refund_cons: String?
    public let producer_info: String?
    
    public init(account_name: String, head_block_num: Int, head_block_time: Date, empowerchains: Array<String>, privileged: Bool, updateable: Bool, last_code_update: Date, created: Date, core_liquid_balance: String?, ram_quota: Int64, net_weight: Int64, cpu_weight: Int64, net_limit: AccountResourceLimit, cpu_limit: AccountResourceLimit, ram_usage: Int64, permissions: Array<AccountPermission>, chain_resource: Array<ChainResource>) {
        self.account_name = account_name
        self.head_block_num = head_block_num
        self.head_block_time = head_block_time
        self.empowerchains = empowerchains
        self.privileged = privileged
        self.updateable = updateable
        self.last_code_update = last_code_update
        self.created = created
        self.core_liquid_balance = core_liquid_balance
        self.ram_quota = ram_quota
        self.net_weight = net_weight
        self.cpu_weight = cpu_weight
        self.net_limit = net_limit
        self.cpu_limit = cpu_limit
        self.ram_usage = ram_usage
        self.permissions = permissions
        self.chain_resource = chain_resource
        
        self.self_delegated_consensus = nil
        self.refund_cons = nil
        self.producer_info = nil
    }
}
