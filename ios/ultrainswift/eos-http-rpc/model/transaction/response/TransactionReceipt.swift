import Foundation

public struct TransactionReceipt : Codable {
    public let receiver: String
    public let act_digest: String
    public let global_sequence: Int64
    public let recv_sequence: Int64
//    public let auth_sequence: Array<(String, Int64)>
    public let code_sequence: Int64
    public let abi_sequence: Int64

    public init(receiver: String, act_digest: String, global_sequence: Int64, recv_sequence: Int64, code_sequence: Int64, abi_sequence: Int64) {
        self.receiver = receiver
        self.act_digest = act_digest
        self.global_sequence = global_sequence
        self.recv_sequence = recv_sequence
//        self.auth_sequence = auth_sequence
        self.code_sequence = code_sequence
        self.abi_sequence = abi_sequence
    }
    
    enum CodingKeys: String, CodingKey {
        case receiver
        case act_digest
        case global_sequence
        case recv_sequence
        case code_sequence
        case abi_sequence
    }
}
