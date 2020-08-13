import Foundation

public struct AccountResourceLimit : Codable {
    public let used: Int64
    public let available: Int64
    public let max: Int64

    public init(used: Int64, available: Int64, max: Int64) {
        self.used = used
        self.available = available
        self.max = max
    }
}
