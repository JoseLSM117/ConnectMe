import { Exception } from "@src/shared/domain/exceptions/Exception";

export class SendEmailException extends Exception {
  constructor(public readonly reason: "transport" | "validation", message:string) {
    super(message);
  }
}