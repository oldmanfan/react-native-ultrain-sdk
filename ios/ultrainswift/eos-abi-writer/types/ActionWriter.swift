//
//  ActionWriter.swift
//  eosswift
//
//  Created by fanliangqin on 2020/7/22.
//  Copyright © 2020 memtrip. All rights reserved.
//

import Foundation

protocol ActionWriter : AbiTypeWriter {
}

public class ActionWriterValue : ActionWriter, Encodable {

    private let NAME_MAX_LENGTH = 21

    private let name: String

    public init(name: String) {
        self.name = name
    }
    
    func encode(writer: AbiEncodingContainer) throws {
        let (hsb, lsb) = actionAsInt64(name: name)
        try writer.encode(hsb)
        try writer.encode(lsb)
    }

    func actionAsInt64(name: String) -> (Int64, Int64) {
        let len = name.count

        var temp: Int64 = 0
        var lsb: Int64 = 0
        var hsb: Int64 = 0
        
        for i in 0..<len {
            let sym = charToSymbol(c: name[i].toUInt8());
            if (i <= 9) {
                temp |= (sym << (6 * i));
            } else if (i == 10) {
                let rb4 = (sym & 0xF);
                temp |= (rb4 << (6 * i));
                lsb = temp;

                temp = ((sym & 0x30) >> 4);
            } else {
                temp |= (sym << (6 * (i - 11) + 2));
            }

            if (len <= 10) { lsb = temp; }
            else { hsb = temp; }
        }

        return (hsb, lsb)
    }

    private func charToSymbol(c: UTF8Char) -> Int64 {

        if (c == 0x2E) {// .
            return Int64.init(0);
        }
        if (c == 0x5F) {// _
            return 1;
        }

        if (c >= 0x30 && c <= 0x39) {// 0 ~ 9
            return Int64.init(c - 0x30 + 2);
        }

        if (c >= 0x61 && c <= 0x7a) {// a ~ z
            return Int64.init(c - 0x61 + 12);
        }

        if (c >= 0x41 && c <= 0x5A) {// A ~ Z
            return Int64.init(c - 0x41 + 38);
        }

        return Int64.init(0xFF) // ERROR
    }
}

