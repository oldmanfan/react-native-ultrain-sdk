import Foundation

public struct AccountAuthWait : Codable {
    public let wait_sec: Int64
    public let weight: Int
    
    public init(wait_sec: Int64, weight: Int) {
        self.wait_sec = wait_sec
        self.weight = weight
    }
}
