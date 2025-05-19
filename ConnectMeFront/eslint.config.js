import js from '@eslint/js';
import globals from 'globals';
import reactHooks from 'eslint-plugin-react-hooks';
import reactRefresh from 'eslint-plugin-react-refresh';
import tseslint from 'typescript-eslint';
import reactPlugin from 'eslint-plugin-react';
export default tseslint.config(
  { ignores: ['dist'] },
  {
    extends: [js.configs.recommended, ...tseslint.configs.recommended],
    files: ['**/*.{ts,tsx}'],
    languageOptions: {
      ecmaVersion: 2020,
      globals: globals.browser,
    },
    plugins: {
      'react': reactPlugin,
      'react-hooks': reactHooks,
      'react-refresh': reactRefresh,
      /* prettier: eslintPluginPrettier */
    },
    rules: {
      ...reactHooks.configs.recommended.rules,
      'react-refresh/only-export-components': [
        'warn',
        { allowConstantExport: true },
      ],
      // Reglas base de JavaScript/TypeScript
      'semi': ['error', 'always'],
      'quotes': [
        'error',
        'single',
        { avoidEscape: true, allowTemplateLiterals: true },
      ],
      'indent': ['error', 2, { SwitchCase: 1 }],
      'comma-dangle': ['error', 'always-multiline'],
      'object-curly-spacing': ['error', 'always'],
      'array-bracket-spacing': ['error', 'never'],
      // Reglas de React para React 17+
      'react/jsx-uses-react': 'off', // Desactivado en React 17+
      'react/react-in-jsx-scope': 'off', // Desactivado en React 17+
      'react/jsx-uses-vars': 'error',
      'react/jsx-curly-spacing': ['error', { when: 'never', children: true }],
      'react/jsx-tag-spacing': ['error', { beforeSelfClosing: 'always' }],
      'react/jsx-closing-bracket-location': ['error', 'line-aligned'],
      'react/jsx-boolean-value': ['error', 'never'],
      'react/self-closing-comp': 'error',
      'react/no-unescaped-entities': 'warn',
      // React Hooks
      ...reactHooks.configs.recommended.rules,
      // React Refresh
      'react-refresh/only-export-components': [
        'warn',
        { allowConstantExport: true },
      ],
      // Buenas prácticas
      'no-console': ['off', { allow: ['warn', 'error'] }],
      'no-debugger': 'error',
      'no-unused-vars': 'off', // Desactivamos para usar la versión de TypeScript
      '@typescript-eslint/no-unused-vars': ['error'],
      'prefer-const': 'error',
      'arrow-body-style': ['error', 'as-needed'],
      'no-multiple-empty-lines': ['error', { max: 1 }],
      'eol-last': ['error', 'always'],
      'keyword-spacing': [
        'error',
        {
          before: true, // Espacio antes de la palabra clave (if, for, etc.)
          after: true, // Espacio después de la palabra clave y antes del paréntesis
        },
      ],
      'max-len': ['error', { code: 80, ignoreUrls: true }],
      'jsx-quotes': ['error', 'prefer-single'],
      'quote-props': ['error', 'consistent-as-needed'],

      /* 'prettier/prettier': ['error', prettierConfig] */
    },
    settings: {
      react: {
        version: 'detect', // Detecta automáticamente la versión de React
      },
    },
  }
);
