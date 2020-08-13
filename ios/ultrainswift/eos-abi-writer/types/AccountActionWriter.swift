//
//  AccountActionWriter.swift
//  eosswift
//
//  Created by fanliangqin on 2020/7/22.
//  Copyright © 2020 memtrip. All rights reserved.
//

import Foundation

protocol AccountActionWriter : AbiTypeWriter {
}

public class AccountActionWriterValue : AccountActionWriter, Encodable {

    private let MAX_LENGTH = 21
    private let ILLEGAL_CHARACTER = "."

    private let name: String

    public init(name: String) {
        self.name = name
    }

    func value() -> String {
        return name
    }

    func encode(writer: AbiEncodingContainer) throws {
        if (name.count > MAX_LENGTH) {
            throw AccountActionError.error("Account action cannot be more than 21 characters. => $name")
        }
        try ActionWriterValue(name: name).encode(writer: writer)
    }

    enum AccountActionError: Error {
        case error(String)
    }
}

