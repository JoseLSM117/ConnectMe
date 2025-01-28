import { StringValueObject } from "@src/shared/domain/value-object/StringValueObject";
import { InvalidEmail, InvalidFirstNameOrLastName, InvalidPassword } from "./Exceptions";

export class UserFirstName extends StringValueObject {
  constructor(value: string) {
    super(value);
    this.ensureValidLength(value);
  }

  private ensureValidLength(value: string): void {
    if (value.length < 2 || value.length > 60) {
      throw new InvalidFirstNameOrLastName();
    }
  }
}

export class UserLastName extends UserFirstName { }

export class UserEmail extends StringValueObject {
  constructor(value: string) {
    super(value);
    this.ensureValidEmail(value);
  }

  private ensureValidEmail(value: string): void {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!regex.test(value)) {
      throw new InvalidEmail();
    }
  }
}

export class UserPassword extends StringValueObject {
  constructor(value: string) {
    super(value);
    this.ensureValidLength(value);
  }

  private ensureValidLength(value: string): void {
    if (value.length < 6 || value.length > 60) {
      throw new InvalidPassword();
    }
  }
}

export class UserProfilePicture extends StringValueObject {}
