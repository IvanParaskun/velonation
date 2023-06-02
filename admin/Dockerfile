FROM node:19-alpine AS builder

ADD . /app
WORKDIR /app
RUN npm install && npm run build

FROM node:19-alpine 

WORKDIR /app

COPY --from=builder /app/build /build
COPY --from=builder /app/package.json /app/package-lock.json /

RUN npm install --production

ENV PORT 5050
EXPOSE 5050