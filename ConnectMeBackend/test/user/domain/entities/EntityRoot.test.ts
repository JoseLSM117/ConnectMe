import { EntityRoot } from "@src/user/domain/entities/EntityRoot"

describe("EntityRoot", () => {
  interface PrimitiveData {
    id: string
    name: string
  }
  class TestEntityRoot extends EntityRoot<TestEntityRoot, PrimitiveData> {
    private readonly id: string
    private readonly name: string
    constructor({ id, name }: { id: string, name: string }) {
      super()
      this.id = id
      this.name = name
    }

    toPrimitives(): PrimitiveData {
      return {
        id: this.id,
        name: this.name
      }
    }
  }
  test("should have a toPrimitives method", () => {
    const entity = {
      id: "id",
      name: "name"
    }
    const testEntity = new TestEntityRoot(entity)
    expect(testEntity.toPrimitives()).toEqual(entity)
  })
})
