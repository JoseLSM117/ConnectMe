"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const config = {
    collectCoverage: true,
    coverageDirectory: "coverage",
    coverageProvider: "v8",
    moduleNameMapper: {
        "^@src/(.*)$": "<rootDir>/src/$1"
    },
    preset: "ts-jest",
    testEnvironment: "jest-environment-node",
    transform: {
        "^.+\\.(ts|tsx)$": "ts-jest"
    },
    moduleFileExtensions: ["ts", "tsx", "js", "json"]
};
exports.default = config;
