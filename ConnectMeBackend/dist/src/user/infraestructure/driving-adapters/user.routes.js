"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.userRoutes = void 0;
const express_1 = require("express");
const UserControllers_1 = require("./UserControllers");
exports.userRoutes = (0, express_1.Router)();
const userControllers = new UserControllers_1.UserControllers();
exports.userRoutes.get("/", userControllers.initRoutes);
