/* eslint-disable @typescript-eslint/no-unsafe-member-access */
/* eslint-disable @typescript-eslint/no-unsafe-call */
import type { JwtService } from "@src/shared/domain/ports/JwtService";
import jwt, { JsonWebTokenError } from "jsonwebtoken";
import { envs } from "../../env/envs";
import { CreateAccessTokenException, CreateRefreshTokenException, CreateTokenException } from "./Exceptions";
export class JwtServiceImpl implements JwtService {
  private readonly secretAccessKey: string = envs.JWT_ACCESS_SECRET_KEY;
  private readonly secretRefreshKey: string = envs.JWT_REFRESH_SECRET_KEY;
  private readonly accesTokenExpires: number = Number(envs.ACCESS_TOKEN_EXPIRES_IN);
  private readonly refreshTokenExpires: number = Number(envs.REFRESH_TOKEN_EXPIRES_IN);

  createAccessToken({ body }: { body: object }): string {
    try {
      return jwt.sign(body, this.secretAccessKey, { expiresIn: this.accesTokenExpires }) as string;
    } catch (error) {
      if (error instanceof JsonWebTokenError) {
        throw new CreateAccessTokenException(error.message as string);
      } else {
        throw new Error('An unexpected error occurred');
      }
    }
  }
  createRefreshToken({ body }: { body: object }): string {
    try {
      return jwt.sign(body, this.secretRefreshKey, { expiresIn: this.refreshTokenExpires }) as string;
    } catch (error) {
      if (error instanceof JsonWebTokenError) {
        throw new CreateRefreshTokenException(error.message as string);
      } else {
        throw new Error('An unexpected error occurred');
      }
    }
  }
  createJwt({ body, secret, expiresIn }: { body: object, secret: string, expiresIn: number }): string {
    try {
      return jwt.sign(body, secret, { expiresIn }) as string;
    } catch (error) {
      if (error instanceof JsonWebTokenError) {
        throw new CreateTokenException(error.message as string);
      } else {
        throw new Error('An unexpected error occurred');
      }
    }
  }
}