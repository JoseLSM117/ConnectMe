export interface JwtService {
  createAccessToken: ({ body }: { body: object })=>string
  createRefreshToken: ({ body }: { body: object })=>string
  createJwt: ({ body, secret, expiresIn }: { body: object, secret: string, expiresIn: number }) => string
}