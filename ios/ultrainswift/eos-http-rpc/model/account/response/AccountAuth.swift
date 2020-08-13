import Foundation

public struct AccountAuth : Codable {
    public let permission: AccountAuthPermission
    public let weight: Int
    
    public init(permission: AccountAuthPermission, weight: Int) {
        self.permission = permission
        self.weight = weight
    }
}
