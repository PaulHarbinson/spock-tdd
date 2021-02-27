package com.example.spock

import spock.lang.Specification
import spock.lang.Subject

class TutorialSpecification extends Specification {

    def "should demonstrate given-when-then"() {
        given:
        def polygon = new Polygon(4)

        when:
        int sides = polygon.numberOfSides

        then:
        sides == 4
    }

    def "should expect exceptions"() {
        when:
        new Polygon(0)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == 0
    }

    def "should expect an Exception to be thrown for invalid input: #sides"() {
        when:
        new Polygon(sides)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == sides

        where:
        sides << [-1, 0, 1, 2]
    }

    def "should be able to create a polygon with #sides sides"() {
        expect:
        new Polygon(sides).numberOfSides == sides

        where:
        sides << [3, 4, 5, 8, 14]
    }

    def "should use data tables for calculating max. Max of #a and #b is #max"() {
        expect:
        Math.max(a, b) == max

        where:
        a | b || max
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    def "should be able to mock a concrete class"() {
        given:
//        Renderer renderer = Mock()  // use this style if IDE/code needs to recognise the type
        def renderer = Mock(Renderer)   // dynamic typing
        @Subject    // not a functional attribute, just labels the object under test for enhanced readability
        def polygon = new Polygon(4, renderer)

        when:
        polygon.draw()

        then:
        4 * renderer.drawLine()
    }

    def "should be able to create a Stub"() {
        given:
        Palette palette = Stub()
        palette.getPrimaryColour() >> Colour.Red
        @Subject
        def renderer = new Renderer(palette)

        expect:
        renderer.getForegroundColour() == Colour.Red
    }

    def "should use a helper method"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        polygon.numberOfSides == 4
        polygon.renderer == renderer
        // polygon could have a long list of attributes to test here
    }
}
