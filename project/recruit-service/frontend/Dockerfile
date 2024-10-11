# Step 1: Build the Next.js app using Node.js
FROM node:18-alpine AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the package.json and package-lock.json (or yarn.lock) files
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application files
COPY . .

# Build the Next.js app
RUN npm run build

# Step 2: Serve the app using a minimal web server
FROM node:18-alpine AS runner

# Set the environment variable for production
ENV NODE_ENV production

# Set the working directory
WORKDIR /app

# Copy only the necessary files from the builder stage
COPY --from=builder /app/package*.json ./
COPY --from=builder /app/.next ./.next
COPY --from=builder /app/public ./public

# Install only production dependencies
RUN npm install --production

# Expose the port the app will run on
EXPOSE 3000

# Command to run the Next.js app
CMD ["npm", "start"]