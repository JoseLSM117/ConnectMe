import { DateValueObject } from "@src/shared/domain/value-object/DateValueObject"

describe("DateValueObject", () => {
  class TestDateValueObject extends DateValueObject { }

  test("Should create a DateValueObject instance", () => {
    const dateValueObject = new TestDateValueObject(new Date())
    expect(dateValueObject).toBeInstanceOf(DateValueObject)
  })

  test("Should return the DateValueObject value", () => {
    const date = new Date()
    const dateValueObject = new TestDateValueObject(date)
    expect(dateValueObject.value).toBe(date)
  })

  test("Should compare two DateValueObject instances", () => {
    const date = new Date()
    const dateValueObject1 = new TestDateValueObject(date)
    const dateValueObject2 = new TestDateValueObject(date)
    expect(dateValueObject1.equals(dateValueObject2)).toBe(true)
  })

  test("Should convert DateValueObject to string", () => {
    const date = new Date()
    const dateValueObject = new TestDateValueObject(date)
    expect(dateValueObject.toString()).toBe(date.toString())
  })
})
