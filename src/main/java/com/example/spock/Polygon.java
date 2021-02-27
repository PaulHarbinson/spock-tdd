package com.example.spock;

class Polygon {
    private final int numberOfSides;
    private Renderer renderer;

    public Polygon(int numberOfSides) {
        if (numberOfSides <= 2) {
            throw new TooFewSidesException("The shape must have more than 2 sides", numberOfSides);
        }
        this.numberOfSides = numberOfSides;
    }

    public Polygon(int numberOfSides, Renderer renderer) {
        this.numberOfSides = numberOfSides;
        this.renderer = renderer;
    }

    public int getNumberOfSides() {
        return numberOfSides;
    }
    public Renderer getRenderer() { return renderer; }

    public void draw() {
        for (int i = 0; i < numberOfSides; i++) {
            renderer.drawLine();
        }
    }
}
