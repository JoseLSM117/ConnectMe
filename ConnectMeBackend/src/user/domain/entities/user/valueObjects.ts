import { StringValueObject } from "@src/shared/domain/value-object/StringValueObject";
import { InvalidEmail, InvalidFirstNameOrLastName, InvalidGender, InvalidPassword } from "./Exceptions";
import { BooleanValueObject } from "@src/shared/domain/value-object/BooleanValueObject";
import { NumberValueObject } from "@src/shared/domain/value-object/IntValueObject";

export class UserVerify extends BooleanValueObject { }
export class UserId extends StringValueObject {
}
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

export class UserProfilePicture extends StringValueObject { }

export class UserCountry extends StringValueObject { }

export class UserPhoneId extends NumberValueObject { }

export class UserGender extends StringValueObject {
  constructor(value: string) {
    super(value);
    this.ensureValidGender(value);
  }
  private ensureValidGender(value: string): void {
    if (value === "Famale" || value === "Male") {
      return;
    }
    throw new InvalidGender();
  }
}
export class UserStatus extends NumberValueObject { }
export class UserRT extends NumberValueObject { }