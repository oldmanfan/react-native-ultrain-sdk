import Foundation

extension Encodable {
    public func toHex(_ abiEncoder: AbiEncoder = AbiEncoder(capacity: 512)) -> String {
        try! abiEncoder.encode(encodable: self)
        return abiEncoder.toData().hexEncodedString()
    }

    public func toData(_ abiEncoder: AbiEncoder = AbiEncoder(capacity: 512)) -> Data {
        try! abiEncoder.encode(encodable: self)
        return abiEncoder.toData()
    }
}

public protocol JSONType: Codable {
    var jsonValue: Any? { get }
}

extension Int: JSONType {
    public var jsonValue: Any? { return self }
}
extension String: JSONType {
    public var jsonValue: Any? { return self }
}
extension Double: JSONType {
    public var jsonValue: Any? { return self }
}
extension Bool: JSONType {
    public var jsonValue: Any? { return self }
}

public struct AnyJSONType: JSONType {
    public var jsonValue: Any?

    public init(from decoder: Decoder) throws {
        let container = try decoder.singleValueContainer()

        if let intValue = try? container.decode(Int.self) {
            jsonValue = intValue
        } else if let stringValue = try? container.decode(String.self) {
            jsonValue = stringValue
        } else if let boolValue = try? container.decode(Bool.self) {
            jsonValue = boolValue
        } else if let doubleValue = try? container.decode(Double.self) {
            jsonValue = doubleValue
        } else if let doubleValue = try? container.decode(Array<AnyJSONType>.self) {
            jsonValue = doubleValue
        } else if let doubleValue = try? container.decode(Dictionary<String, AnyJSONType>.self) {
            jsonValue = doubleValue
        } else {
            jsonValue = nil
        }
    }
    
    public func encode(to encoder: Encoder) throws {
        var container = encoder.singleValueContainer()
        if let v = jsonValue as? Int {
            try container.encode(v)
        } else if let v = jsonValue as? Bool {
            try container.encode(v)
        } else if let v = jsonValue as? Double {
            try container.encode(v)
        } else if let v = jsonValue as? Array<AnyJSONType> {
            try container.encode(v)
        } else if let v = jsonValue as? Dictionary<String, AnyJSONType> {
            try container.encode(v)
        } else if let v = jsonValue as? String {
            try container.encode(v)
        }
    }
}
