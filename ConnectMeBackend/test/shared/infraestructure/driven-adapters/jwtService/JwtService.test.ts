import { JwtServiceImpl } from "@src/shared/infaestructure/driven-adapters/jwtService/JwtService"
import { envs } from "../../../../../src/shared/infaestructure/env/envs";
import jwt, { JsonWebTokenError } from "jsonwebtoken";
import { CreateAccessTokenException, CreateRefreshTokenException, CreateTokenException } from "@src/shared/infaestructure/driven-adapters/jwtService/Exceptions";
jest.mock("../../../../../src/shared/infaestructure/env/envs", () => ({
  envs: {
    JWT_ACCESS_SECRET_KEY: "JWT_ACCESS_SECRET_KEY",
    JWT_REFRESH_SECRET_KEY: "JWT_REFRESH_SECRET_KEY",
    ACCESS_TOKEN_EXPIRES_IN: "3600",
    REFRESH_TOKEN_EXPIRES_IN: "18000"
  }
}))
jest.mock("jsonwebtoken", () => ({
  sign: jest.fn().mockReturnValue("validToken"),
  JsonWebTokenError: jest.fn().mockImplementation((message:string)=>({
    name: "JsonWebTokenError",
    message
  }))
}))
const body = {
  FirstName: "Jhon",
  LastName: "Doe"
}
describe("JwtService", () => {
  const jwtService = new JwtServiceImpl();
  afterEach(()=>{
    jest.resetAllMocks()
  })
  test("Should create correctly accessToken with body param", () => {
    const token = jwtService.createAccessToken({ body });
    expect(jwt.sign).toHaveBeenCalledWith(body, "JWT_ACCESS_SECRET_KEY", { expiresIn: 3600 })
    expect(token).toBe("validToken")
  })

  test("Should throw CreateAccessTokenException", () => {
    (jwt.sign as jest.Mock).mockImplementation(() => { throw new JsonWebTokenError("JWT error") })
    expect(() => jwtService.createAccessToken({ body })).toThrow(CreateAccessTokenException)
  })

  test("Should throw an unknow exception", () => {
    (jwt.sign as jest.Mock).mockImplementation(() => { throw new Error("JWT error") })
    expect(() => jwtService.createAccessToken({ body })).toThrow(Error)
  })

  test("Should create correctly refreshToken with body param", () => {
    (jwt.sign as jest.Mock).mockReturnValue("validToken");
    const token = jwtService.createRefreshToken({ body });
    expect(jwt.sign).toHaveBeenCalledWith(body, "JWT_REFRESH_SECRET_KEY", { expiresIn: 18000 })
    expect(token).toBe("validToken")
  })

  test("Should throw CreateRefreshTokenException", () => {
    (jwt.sign as jest.Mock).mockImplementation(() => { throw new JsonWebTokenError("JWT error") })
    expect(() => jwtService.createRefreshToken({ body })).toThrow(CreateRefreshTokenException)
  })

  test("Should throw an unknow exception", () => {
    (jwt.sign as jest.Mock).mockImplementation(() => { throw new Error("JWT error") })
    expect(() => jwtService.createRefreshToken({ body })).toThrow(Error)
  })

  test("Should create correctly Jwt", () => {
    (jwt.sign as jest.Mock).mockReturnValue("validToken");
    const expiresIn = 3600;
    const secret = "test"
    const token = jwtService.createJwt({ body, expiresIn, secret });
    expect(jwt.sign).toHaveBeenCalledWith(body, secret, { expiresIn })
    expect(token).toBe("validToken")
  })
  test("Should throw CreateTokenException", () => {
    (jwt.sign as jest.Mock).mockImplementation(() => { throw new JsonWebTokenError("JWT error") })
    const expiresIn = 3600;
    const secret = "test"
    expect(() => jwtService.createJwt({ body, expiresIn, secret })).toThrow(CreateTokenException)
  })

  test("Should throw an unknow exception", () => {
    (jwt.sign as jest.Mock).mockImplementation(() => { throw new Error("JWT error") })
    const expiresIn = 3600;
    const secret = "test"
    expect(() => jwtService.createJwt({ body, expiresIn, secret })).toThrow(Error)
  })
})