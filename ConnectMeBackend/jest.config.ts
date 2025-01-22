import type { Config } from "jest"

const config: Config = {
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
}

export default config
