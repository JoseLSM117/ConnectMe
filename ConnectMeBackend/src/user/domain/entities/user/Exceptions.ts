import { Exception } from "@src/shared/domain/exceptions/Exception";

export class InvalidFirstNameOrLastName extends Exception {
  constructor() {
    super("The firstname should be between 2 and 50 characters");
  }
}

export class InvalidEmail extends Exception {
  constructor() {
    super("Invalid email");
  }
}

export class InvalidPassword extends Exception {
  constructor() {
    super("The password should be between 6 and 60 characters");
  }
}
export class InvalidGender extends Exception{
  constructor(){
    super("This gender not exist");
  }
}