FROM node:22 AS base
WORKDIR /usr/local/app

FROM base AS client-base
COPY package.json .
RUN npm install
COPY . .

EXPOSE 3000

CMD [ "npm", "run", "dev" ]