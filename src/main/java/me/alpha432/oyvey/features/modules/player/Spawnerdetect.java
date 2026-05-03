// Iterate through loaded chunks in a specific radius
for (Chunk chunk : loadedChunks) {
    for (int x = 0; x < 16; x++) {
        for (int z = 0; z < 16; z++) {
            // Scan from current height down to bedrock
            for (int y = playerY; y >= -64; y--) {
                BlockPos pos = new BlockPos(chunk.getPos().getStartX() + x, y, chunk.getPos().getStartZ() + z);
                if (world.getBlockState(pos).isOf(Blocks.SPAWNER)) {
                    this.notifyStaff(pos); // Logic to trigger the highlight
                }
            }
        }
    }
}
