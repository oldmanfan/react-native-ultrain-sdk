import Foundation

public struct TransactionAct : Codable {
    public let account: String
    public let name: String
    public let authorization: Array<TransactionActAuthorization>
    public let hex_data: String?
    public let data: [String: AnyJSONType]

    public init(account: String, name: String, authorization: Array<TransactionActAuthorization>, hex_data: String?, data: [String: AnyJSONType]) {
        self.account = account
        self.name = name
        self.authorization = authorization
        self.hex_data = hex_data
        self.data = data
    }
    
    public init(from decoder: Decoder) throws {
        let keyedContainer = try decoder.container(keyedBy: CodingKeys.self)
        self.account = try keyedContainer.decode(String.self, forKey: .account)
        self.name = try keyedContainer.decode(String.self, forKey: .name)
        self.authorization = try keyedContainer.decode(Array<TransactionActAuthorization>.self, forKey: .authorization)
        self.hex_data = try keyedContainer.decodeIfPresent(String.self, forKey: .hex_data)

        do {
            self.data = try keyedContainer.decode([String: AnyJSONType].self, forKey: .data)
        } catch {
            self.data = [:]
        }
    }

    enum CodingKeys: String, CodingKey {
        case account
        case name
        case authorization
        case hex_data
        case data
    }

    public func encode(to encoder: Encoder) throws {
        var container = encoder.container(keyedBy: CodingKeys.self)
        try container.encode(account, forKey: .account)
        try container.encode(name, forKey: .name)
        try container.encode(authorization, forKey: .authorization)
        try container.encode(hex_data, forKey: .hex_data)
        do {
            try container.encode(data, forKey: .data)
        } catch {
            try container.encode("{}", forKey: .data)
        }
    }
}
