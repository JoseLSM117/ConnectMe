/*
  Warnings:

  - The primary key for the `Messages` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `attachment_url` on the `Messages` table. All the data in the column will be lost.
  - You are about to drop the column `chat_id` on the `Messages` table. All the data in the column will be lost.
  - You are about to drop the column `content` on the `Messages` table. All the data in the column will be lost.
  - You are about to drop the column `create_at` on the `Messages` table. All the data in the column will be lost.
  - You are about to drop the column `id` on the `Messages` table. All the data in the column will be lost.
  - You are about to drop the column `update_at` on the `Messages` table. All the data in the column will be lost.
  - You are about to drop the column `user_id` on the `Messages` table. All the data in the column will be lost.
  - The primary key for the `MessagesReads` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `id` on the `MessagesReads` table. All the data in the column will be lost.
  - You are about to drop the column `message_id` on the `MessagesReads` table. All the data in the column will be lost.
  - You are about to drop the column `read_at` on the `MessagesReads` table. All the data in the column will be lost.
  - You are about to drop the column `user_id` on the `MessagesReads` table. All the data in the column will be lost.
  - The primary key for the `RefreshToken` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `id` on the `RefreshToken` table. All the data in the column will be lost.
  - You are about to drop the column `token` on the `RefreshToken` table. All the data in the column will be lost.
  - The primary key for the `UserStatus` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `id` on the `UserStatus` table. All the data in the column will be lost.
  - You are about to drop the column `status` on the `UserStatus` table. All the data in the column will be lost.
  - The primary key for the `Users` table will be changed. If it partially fails, the table could be left without primary key constraint.
  - You are about to drop the column `create_at` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the column `email` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the column `first_name` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the column `id` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the column `isVerify` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the column `last_name` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the column `password` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the column `profile_picture` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the column `refresh_token_id` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the column `status_id` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the column `update_at` on the `Users` table. All the data in the column will be lost.
  - You are about to drop the `ChatParticipants` table. If the table is not empty, all the data it contains will be lost.
  - You are about to drop the `Chats` table. If the table is not empty, all the data it contains will be lost.
  - A unique constraint covering the columns `[mr_user_id]` on the table `MessagesReads` will be added. If there are existing duplicate values, this will fail.
  - A unique constraint covering the columns `[user_email]` on the table `Users` will be added. If there are existing duplicate values, this will fail.
  - A unique constraint covering the columns `[user_phone_id]` on the table `Users` will be added. If there are existing duplicate values, this will fail.
  - A unique constraint covering the columns `[user_status_id]` on the table `Users` will be added. If there are existing duplicate values, this will fail.
  - A unique constraint covering the columns `[user_rt_id]` on the table `Users` will be added. If there are existing duplicate values, this will fail.
  - Added the required column `message_chat_id` to the `Messages` table without a default value. This is not possible if the table is not empty.
  - Added the required column `message_content` to the `Messages` table without a default value. This is not possible if the table is not empty.
  - The required column `message_id` was added to the `Messages` table with a prisma-level default value. This is not possible if the table is not empty. Please add this column as optional, then populate it before making it required.
  - Added the required column `message_update_at` to the `Messages` table without a default value. This is not possible if the table is not empty.
  - Added the required column `message_user_id` to the `Messages` table without a default value. This is not possible if the table is not empty.
  - The required column `mr_id` was added to the `MessagesReads` table with a prisma-level default value. This is not possible if the table is not empty. Please add this column as optional, then populate it before making it required.
  - Added the required column `mr_message_id` to the `MessagesReads` table without a default value. This is not possible if the table is not empty.
  - Added the required column `mr_read_at` to the `MessagesReads` table without a default value. This is not possible if the table is not empty.
  - Added the required column `mr_user_id` to the `MessagesReads` table without a default value. This is not possible if the table is not empty.
  - Added the required column `rt_token` to the `RefreshToken` table without a default value. This is not possible if the table is not empty.
  - Added the required column `status_last_seen` to the `UserStatus` table without a default value. This is not possible if the table is not empty.
  - Added the required column `status_name` to the `UserStatus` table without a default value. This is not possible if the table is not empty.
  - Added the required column `user_email` to the `Users` table without a default value. This is not possible if the table is not empty.
  - Added the required column `user_first_name` to the `Users` table without a default value. This is not possible if the table is not empty.
  - The required column `user_id` was added to the `Users` table with a prisma-level default value. This is not possible if the table is not empty. Please add this column as optional, then populate it before making it required.
  - Added the required column `user_last_name` to the `Users` table without a default value. This is not possible if the table is not empty.
  - Added the required column `user_password` to the `Users` table without a default value. This is not possible if the table is not empty.
  - Added the required column `user_rt_id` to the `Users` table without a default value. This is not possible if the table is not empty.
  - Added the required column `user_update_at` to the `Users` table without a default value. This is not possible if the table is not empty.

*/
-- CreateEnum
CREATE TYPE "Gender" AS ENUM ('Male', 'Famale');

-- DropForeignKey
ALTER TABLE "ChatParticipants" DROP CONSTRAINT "ChatParticipants_chat_id_fkey";

-- DropForeignKey
ALTER TABLE "ChatParticipants" DROP CONSTRAINT "ChatParticipants_user_id_fkey";

-- DropForeignKey
ALTER TABLE "Chats" DROP CONSTRAINT "Chats_last_message_id_fkey";

-- DropForeignKey
ALTER TABLE "Messages" DROP CONSTRAINT "Messages_chat_id_fkey";

-- DropForeignKey
ALTER TABLE "Messages" DROP CONSTRAINT "Messages_user_id_fkey";

-- DropForeignKey
ALTER TABLE "MessagesReads" DROP CONSTRAINT "MessagesReads_message_id_fkey";

-- DropForeignKey
ALTER TABLE "MessagesReads" DROP CONSTRAINT "MessagesReads_user_id_fkey";

-- DropForeignKey
ALTER TABLE "Users" DROP CONSTRAINT "Users_refresh_token_id_fkey";

-- DropForeignKey
ALTER TABLE "Users" DROP CONSTRAINT "Users_status_id_fkey";

-- DropIndex
DROP INDEX "Messages_create_at_update_at_idx";

-- DropIndex
DROP INDEX "MessagesReads_message_id_user_id_key";

-- DropIndex
DROP INDEX "UserStatus_status_key";

-- DropIndex
DROP INDEX "Users_email_key";

-- DropIndex
DROP INDEX "Users_refresh_token_id_key";

-- DropIndex
DROP INDEX "Users_status_id_key";

-- AlterTable
ALTER TABLE "Messages" DROP CONSTRAINT "Messages_pkey",
DROP COLUMN "attachment_url",
DROP COLUMN "chat_id",
DROP COLUMN "content",
DROP COLUMN "create_at",
DROP COLUMN "id",
DROP COLUMN "update_at",
DROP COLUMN "user_id",
ADD COLUMN     "message_chat_id" TEXT NOT NULL,
ADD COLUMN     "message_content" TEXT NOT NULL,
ADD COLUMN     "message_create_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN     "message_id" TEXT NOT NULL,
ADD COLUMN     "message_is_read" BOOLEAN NOT NULL DEFAULT false,
ADD COLUMN     "message_update_at" TIMESTAMP(3) NOT NULL,
ADD COLUMN     "message_user_id" TEXT NOT NULL,
ADD CONSTRAINT "Messages_pkey" PRIMARY KEY ("message_id");

-- AlterTable
ALTER TABLE "MessagesReads" DROP CONSTRAINT "MessagesReads_pkey",
DROP COLUMN "id",
DROP COLUMN "message_id",
DROP COLUMN "read_at",
DROP COLUMN "user_id",
ADD COLUMN     "mr_id" TEXT NOT NULL,
ADD COLUMN     "mr_message_id" TEXT NOT NULL,
ADD COLUMN     "mr_read_at" TIMESTAMP(3) NOT NULL,
ADD COLUMN     "mr_user_id" TEXT NOT NULL,
ADD CONSTRAINT "MessagesReads_pkey" PRIMARY KEY ("mr_id");

-- AlterTable
ALTER TABLE "RefreshToken" DROP CONSTRAINT "RefreshToken_pkey",
DROP COLUMN "id",
DROP COLUMN "token",
ADD COLUMN     "rt_id" SERIAL NOT NULL,
ADD COLUMN     "rt_token" TEXT NOT NULL,
ADD CONSTRAINT "RefreshToken_pkey" PRIMARY KEY ("rt_id");

-- AlterTable
ALTER TABLE "UserStatus" DROP CONSTRAINT "UserStatus_pkey",
DROP COLUMN "id",
DROP COLUMN "status",
ADD COLUMN     "status_id" SERIAL NOT NULL,
ADD COLUMN     "status_last_seen" TEXT NOT NULL,
ADD COLUMN     "status_name" TEXT NOT NULL,
ADD CONSTRAINT "UserStatus_pkey" PRIMARY KEY ("status_id");

-- AlterTable
ALTER TABLE "Users" DROP CONSTRAINT "Users_pkey",
DROP COLUMN "create_at",
DROP COLUMN "email",
DROP COLUMN "first_name",
DROP COLUMN "id",
DROP COLUMN "isVerify",
DROP COLUMN "last_name",
DROP COLUMN "password",
DROP COLUMN "profile_picture",
DROP COLUMN "refresh_token_id",
DROP COLUMN "status_id",
DROP COLUMN "update_at",
ADD COLUMN     "user_country" TEXT,
ADD COLUMN     "user_create_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,
ADD COLUMN     "user_email" TEXT NOT NULL,
ADD COLUMN     "user_first_name" TEXT NOT NULL,
ADD COLUMN     "user_gender" "Gender",
ADD COLUMN     "user_id" TEXT NOT NULL,
ADD COLUMN     "user_is_verify" BOOLEAN NOT NULL DEFAULT false,
ADD COLUMN     "user_last_name" TEXT NOT NULL,
ADD COLUMN     "user_password" TEXT NOT NULL,
ADD COLUMN     "user_phone_id" INTEGER,
ADD COLUMN     "user_profile_picture" TEXT,
ADD COLUMN     "user_rt_id" INTEGER NOT NULL,
ADD COLUMN     "user_status_id" INTEGER,
ADD COLUMN     "user_update_at" TIMESTAMP(3) NOT NULL,
ADD CONSTRAINT "Users_pkey" PRIMARY KEY ("user_id");

-- DropTable
DROP TABLE "ChatParticipants";

-- DropTable
DROP TABLE "Chats";

-- CreateTable
CREATE TABLE "Phone" (
    "phone_id" SERIAL NOT NULL,
    "phone_country_code" TEXT NOT NULL,
    "phone_number" TEXT NOT NULL,

    CONSTRAINT "Phone_pkey" PRIMARY KEY ("phone_id")
);

-- CreateTable
CREATE TABLE "Contacts" (
    "contact_id" SERIAL NOT NULL,
    "contact_user_id" TEXT NOT NULL,
    "contact_contact_id" TEXT NOT NULL,
    "contact_name" TEXT NOT NULL,
    "contact_update_at" TIMESTAMP(3) NOT NULL,
    "contact_create_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT "Contacts_pkey" PRIMARY KEY ("contact_id")
);

-- CreateTable
CREATE TABLE "IndividualChats" (
    "ic_chat_id" TEXT NOT NULL,
    "ic_user1_id" TEXT NOT NULL,
    "ic_user2_id" TEXT NOT NULL,
    "ic_update_at" TIMESTAMP(3) NOT NULL,
    "ic_create_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT "IndividualChats_pkey" PRIMARY KEY ("ic_chat_id")
);

-- CreateTable
CREATE TABLE "ChatMembers" (
    "member_group_id" TEXT NOT NULL,
    "member_user_id" TEXT NOT NULL,
    "member_joined_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- CreateTable
CREATE TABLE "GroupChats" (
    "group_id" TEXT NOT NULL,
    "group_name" TEXT NOT NULL,
    "group_created_by" TEXT NOT NULL,
    "group_image" TEXT,
    "group_create_at" TIMESTAMP(3) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- CreateIndex
CREATE UNIQUE INDEX "Contacts_contact_contact_id_contact_user_id_key" ON "Contacts"("contact_contact_id", "contact_user_id");

-- CreateIndex
CREATE UNIQUE INDEX "ChatMembers_member_group_id_member_user_id_key" ON "ChatMembers"("member_group_id", "member_user_id");

-- CreateIndex
CREATE UNIQUE INDEX "GroupChats_group_id_key" ON "GroupChats"("group_id");

-- CreateIndex
CREATE UNIQUE INDEX "MessagesReads_mr_user_id_key" ON "MessagesReads"("mr_user_id");

-- CreateIndex
CREATE UNIQUE INDEX "Users_user_email_key" ON "Users"("user_email");

-- CreateIndex
CREATE UNIQUE INDEX "Users_user_phone_id_key" ON "Users"("user_phone_id");

-- CreateIndex
CREATE UNIQUE INDEX "Users_user_status_id_key" ON "Users"("user_status_id");

-- CreateIndex
CREATE UNIQUE INDEX "Users_user_rt_id_key" ON "Users"("user_rt_id");

-- AddForeignKey
ALTER TABLE "Users" ADD CONSTRAINT "Users_user_phone_id_fkey" FOREIGN KEY ("user_phone_id") REFERENCES "Phone"("phone_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Users" ADD CONSTRAINT "Users_user_status_id_fkey" FOREIGN KEY ("user_status_id") REFERENCES "UserStatus"("status_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Users" ADD CONSTRAINT "Users_user_rt_id_fkey" FOREIGN KEY ("user_rt_id") REFERENCES "RefreshToken"("rt_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Contacts" ADD CONSTRAINT "Contacts_contact_user_id_fkey" FOREIGN KEY ("contact_user_id") REFERENCES "Users"("user_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Contacts" ADD CONSTRAINT "Contacts_contact_contact_id_fkey" FOREIGN KEY ("contact_contact_id") REFERENCES "Users"("user_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "IndividualChats" ADD CONSTRAINT "IndividualChats_ic_user1_id_fkey" FOREIGN KEY ("ic_user1_id") REFERENCES "Users"("user_id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "IndividualChats" ADD CONSTRAINT "IndividualChats_ic_user2_id_fkey" FOREIGN KEY ("ic_user2_id") REFERENCES "Users"("user_id") ON DELETE RESTRICT ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ChatMembers" ADD CONSTRAINT "ChatMembers_member_group_id_fkey" FOREIGN KEY ("member_group_id") REFERENCES "GroupChats"("group_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "ChatMembers" ADD CONSTRAINT "ChatMembers_member_user_id_fkey" FOREIGN KEY ("member_user_id") REFERENCES "Users"("user_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Messages" ADD CONSTRAINT "GroupChats" FOREIGN KEY ("message_chat_id") REFERENCES "GroupChats"("group_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Messages" ADD CONSTRAINT "IndividualChats" FOREIGN KEY ("message_chat_id") REFERENCES "IndividualChats"("ic_chat_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "Messages" ADD CONSTRAINT "Messages_message_user_id_fkey" FOREIGN KEY ("message_user_id") REFERENCES "Users"("user_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "MessagesReads" ADD CONSTRAINT "MessagesReads_mr_message_id_fkey" FOREIGN KEY ("mr_message_id") REFERENCES "Messages"("message_id") ON DELETE CASCADE ON UPDATE CASCADE;

-- AddForeignKey
ALTER TABLE "MessagesReads" ADD CONSTRAINT "MessagesReads_mr_user_id_fkey" FOREIGN KEY ("mr_user_id") REFERENCES "Users"("user_id") ON DELETE CASCADE ON UPDATE CASCADE;
