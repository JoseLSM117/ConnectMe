export class Exception extends Error {
  // eslint-disable-next-line @typescript-eslint/no-useless-constructor
  constructor(message?: string) {
    super(message);
  }
}
