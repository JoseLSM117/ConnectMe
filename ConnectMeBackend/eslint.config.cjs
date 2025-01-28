module.exports = (async function config() {
  const { default: love } = await import("eslint-config-love")
  return [
    {
      files: ["src/**/*.js", "src/**/*.ts"],
      ...love,
      rules: {
        ...love.rules,
        semi: "error",
        "@typescript-eslint/no-unsafe-assignment":"off",
        "@typescript-eslint/space-before-function-paren": "off",
        "@typescript-eslint/no-floating-promises": "off",
        "@typescript-eslint/quotes": "off",
        "@typescript-eslint/ban-types": "off",
        "@typescript-eslint/strict-boolean-expressions": "off",
        "@typescript-eslint/prefer-destructuring":"off",
        "no-console":"off",
        "@typescript-eslint/no-magic-numbers":"off",
        "@typescript-eslint/class-methods-use-this":"off",
        "eslint-comments/require-description":"off",
        "@typescript-eslint/no-wrapper-object-types":"off",
        "@typescript-eslint/no-empty-function":"off",
        "@typescript-eslint/no-unnecessary-condition":"off"
      }
    }
  ]
})()