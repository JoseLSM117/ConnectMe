FROM node:22 AS base
WORKDIR /usr/local/app

#Stage front-base

FROM base AS client-base
COPY ConnectMeFront/package.json ConnectMeFront/package-lock.json ./
RUN npm install
COPY ConnectMeFront/babel.config.cjs ConnectMeFront/jest.config.js ConnectMeFront/jest.setup.js ConnectMeFront/eslint.config.js ConnectMeFront/index.html ConnectMeFront/postcss.config.js ConnectMeFront/README.md ConnectMeFront/tailwind.config.js ConnectMeFront/tsconfig.app.json ConnectMeFront/tsconfig.json ConnectMeFront/tsconfig.node.json ConnectMeFront/vite.config.ts ./
COPY ConnectMeFront/public ./public
COPY ConnectMeFront/src ./src
COPY ConnectMeFront/tests ./tests

#Stage client-dev

FROM client-base AS client-dev
CMD ["npm", "run", "dev"]

#Stage client test

FROM client-base AS client-test
RUN npm run test > result.log
RUN cat result.log

#Stage client build

FROM client-base AS client-build
RUN npm run build

#Stage backend-base

FROM base AS backend-base
COPY ConnectMeBackend/package.json ConnectMeBackend/package-lock.json ./
RUN npm install
COPY ConnectMeBackend/eslint.config.cjs ConnectMeBackend/tsconfig.json ./ 
COPY ConnectMeBackend/prisma ./prisma 
COPY ConnectMeBackend/src ./src
COPY ConnectMeBackend/test ./tests

#Stage backend-dev

FROM backend-base as backend-dev
CMD ["npm", "run", "dev"]

#Stage backend-test
FROM backend-base as backend-test
RUN npm run test > result.log
RUN cat result.log

#Stage backend build

FROM backend-base as backend-build
RUN npm run build

FROM base AS final
ENV NODE_ENV=production
COPY --from=backend-test /usr/local/app/package.json /usr/local/app/package-lock.json ./
RUN npm install
COPY --from=backend-build /usr/local/app/dist ./src/server
COPY --from=client-build /usr/local/app/dist ./src/static
EXPOSE 3000
CMD ["node", "src/server/app.js"]