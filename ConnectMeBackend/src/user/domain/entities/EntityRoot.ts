
// eslint-disable-next-line @typescript-eslint/no-unnecessary-type-parameters, @typescript-eslint/no-unused-vars
export abstract class EntityRoot<Entity, PrimitiveData> {
  abstract toPrimitives(): PrimitiveData
}
