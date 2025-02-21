import { EntityRoot } from "../EntityRoot";
import {
  UserCountry,
  UserEmail,
  UserFirstName,
  UserGender,
  UserId,
  UserLastName,
  UserPassword,
  UserPhoneId,
  UserProfilePicture,
  UserRT,
  UserStatus,
  UserVerify
} from "./valueObjects";

interface PrimitiveData {
  userId: string | null
  userFirstName: string
  userLastName: string
  userEmail: string
  userPassword: string
  userProfilePicture: string | null
  userIsVerify: boolean
  userCountry: string | null
  userPhoneId: number | null
  userGender: string | null
  userStatusId: number | null
  userRtId: number | null
}
interface ValueObjectData {
  userId: UserId | null
  userFirstName: UserFirstName
  userLastName: UserLastName
  userEmail: UserEmail
  userPassword: UserPassword
  userProfilePicture: UserProfilePicture | null
  userIsVerify: UserVerify
  userCountry: UserCountry | null
  userPhoneId: UserPhoneId | null
  userGender: UserGender | null
  userStatusId: UserStatus | null
  userRtId: UserRT | null
}
export class UserEntity extends EntityRoot<UserEntity, PrimitiveData> {
  readonly userId: UserId | null;
  readonly userFirstName: UserFirstName;
  readonly userLastName: UserLastName;
  readonly userEmail: UserEmail;
  readonly userPassword: UserPassword;
  readonly userProfilePicture: UserProfilePicture | null;
  readonly userIsVerify: UserVerify;
  readonly userCountry: UserCountry | null;
  readonly userPhoneId: UserPhoneId | null;
  readonly userGender: UserGender | null;
  readonly userStatusId: UserStatus | null;
  readonly userRtId: UserRT | null;
  constructor(user: ValueObjectData) {
    super();
    this.userFirstName = user.userFirstName;
    this.userLastName = user.userLastName;
    this.userEmail = user.userEmail;
    this.userPassword = user.userPassword;
    this.userProfilePicture = user.userProfilePicture;
    this.userIsVerify = user.userIsVerify;
    this.userId = user.userId;
    this.userCountry = user.userCountry;
    this.userPhoneId = user.userPhoneId;
    this.userCountry = user.userCountry;
    this.userGender = user.userGender;
    this.userStatusId = user.userStatusId;
    this.userRtId = user.userRtId;
  }

  static create(user: ValueObjectData): UserEntity {
    const { userEmail, userFirstName, userLastName, userPassword, userProfilePicture, userId, userIsVerify, userCountry, userGender, userStatusId, userRtId, userPhoneId } = user;
    return new UserEntity({
      userEmail,
      userFirstName,
      userLastName,
      userPassword,
      userProfilePicture,
      userId,
      userIsVerify,
      userCountry,
      userGender,
      userPhoneId,
      userStatusId,
      userRtId
    });
  }

  static fromPrimitives(plainData: PrimitiveData): UserEntity {
    return new UserEntity({
      userFirstName: new UserFirstName(plainData.userFirstName),
      userLastName: new UserLastName(plainData.userLastName),
      userEmail: new UserEmail(plainData.userEmail),
      userPassword: new UserPassword(plainData.userPassword),
      userProfilePicture: plainData.userProfilePicture !== null ? new UserProfilePicture(plainData.userProfilePicture) : null,
      userId: plainData.userId !== null ? new UserId(plainData.userId) : null,
      userIsVerify: new UserVerify(plainData.userIsVerify),
      userCountry: plainData.userCountry ? new UserCountry(plainData.userCountry) : null,
      userGender: plainData.userGender ? new UserGender(plainData.userGender) : null,
      userPhoneId: plainData.userPhoneId ? new UserPhoneId(plainData.userPhoneId) : null,
      userStatusId: plainData.userStatusId ? new UserStatus(plainData.userStatusId) : null,
      userRtId: plainData.userRtId ? new UserRT(plainData.userRtId) : null,
    });
  }

  toPrimitives(): PrimitiveData {
    return {
      userId: this.userId ? this.userId.value : null,
      userFirstName: this.userFirstName.value,
      userLastName: this.userLastName.value,
      userEmail: this.userEmail.value,
      userPassword: this.userPassword.value,
      userProfilePicture: this.userProfilePicture ? this.userProfilePicture.value : null,
      userIsVerify: this.userIsVerify.value,
      userCountry: this.userCountry ? this.userCountry.value : null,
      userGender: this.userGender ? this.userGender.value : null,
      userPhoneId: this.userPhoneId ? this.userPhoneId.value : null,
      userStatusId: this.userStatusId ? this.userStatusId.value : null,
      userRtId: this.userRtId ? this.userRtId.value : null
    };
  }
}
