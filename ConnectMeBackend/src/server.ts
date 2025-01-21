import 'module-alias/register'
import express, { type Router } from 'express'

export class Server {
  private readonly _port = 3000
  private readonly _app = express()

  constructor() {
    this._app.use(express.json())
    this._app.use(express.urlencoded({ extended: false }))
  }

  addRoutes(routes: Router[]): void {
    routes.forEach(route => {
      this._app.use(route)
    })
  }

  start(): void {
    this._app.listen(this._port, () => {
      console.log('App listen in port 3000')
    })
  }
}
