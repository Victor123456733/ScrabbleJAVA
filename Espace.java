package scrabble;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;

public class Espace {
    Tile       tile;
    int        x, y;
    String     special;
    static int radius = 20;

    public Espace() {
        tile = null;
        x = 0;
        y = 0;
        special = null;
    }

    public Espace( Tile t ) {
        tile = t;
    }

    public Espace( int x, int y, Tile t ) {
        tile = t;
        this.x = x;
        this.y = y;
        special = null;
    }

    public Espace( int x, int y, Tile t, String str ) {
        tile = t;
        this.x = x;
        this.y = y;
        special = str;
    }

    public Tile getTile() {

        return tile;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial( String s ) {
        special = s;
    }

    public void setTile( Tile t ) {
        tile = t;
    }

    public String toString() {
        return ( tile != null ) ? "" + tile.getVal() : "e";
    }

    public void draw( Graphics g ) {
        Color background = null;
        String val1 = "" + ( tile != null ? tile.getVal() : ( special != null ) ? special : ' ' );
        String val2 = "" + ( tile != null ? tile.getPoints() : "" );
        background = Color.black;
        if ( special == null && tile == null )
            background = Color.white;
        else if ( tile != null && tile.isPermanent() )
            background = new Color( 239, 245, 192 );
        else if ( tile != null && !tile.isPermanent() )
            background = new Color( 182, 186, 147 );
        else if ( special.equals( "Star" ) || special.equals( "DW" ) )
            background = Color.red;
        else if ( special.equals( "DL" ) )
            background = Color.CYAN;
        else if ( special.equals( "TL" ) )
            background = Color.green;
        else if ( special.equals( "TW" ) ) {
            background = Color.orange;
        }

        Polygon hexagon = new Polygon();
        hexagon.addPoint( x, y - radius );
        hexagon.addPoint( x + 20, y - 20 );
        hexagon.addPoint( x + 20, y );
        hexagon.addPoint( x + 20, y + 20 );
        hexagon.addPoint( x, y + radius );
        hexagon.addPoint( x - 20, y + 20 );
        hexagon.addPoint( x - 20, y - 20 );
        g.setColor( background );
        g.fillPolygon( hexagon );
        g.setColor( Color.black );
        g.drawPolygon( hexagon );
        if ( val2.equals( "0" ) )
            g.setColor( new Color( 192, 3, 3 ) );
        if ( val1.equals( "Star" ) ) {
            drawStar( x, y, g );
        } else
            g.drawString( val1, x - (int) ( ( 0.3 ) * radius ), y + (int) ( 0.3 * radius ) );
        g.drawString( val2, x + (int) ( ( 0.25 ) * radius ), y + (int) ( 0.6 * radius ) );
    }

    public boolean contains( double posX, double posY ) {
        double yMod = ( ( Math.sqrt( 3 ) / 2.0 ) * radius ), xMod = ( ( 0.5 ) * radius );
        if ( posX > x + xMod || posX < x - xMod )
            return false;
        if ( posY > y + radius || posY < y - radius )
            return false;
        if ( ( posX > x + xMod && posY < y - yMod ) || ( posX > x + xMod && posY > y + yMod ) ||
                ( posX < x - xMod && posY < y - yMod ) || ( posX < x - xMod && posY > y + yMod ) )
            return false;
        return true;
    }

    public void drawStar( int startX, int startY, Graphics g ) {
        int starRadius = (int) ( ( 0.5 ) * radius ), currX = startX, currY = startY - starRadius;
        int xMod = (int) ( Math.cos( 72 * ( Math.PI / 180 ) ) * starRadius ),
                xMod2 = (int) ( Math.cos( 36 * ( Math.PI / 180 ) ) * starRadius );
        int yMod = (int) ( Math.sin( 72 * ( Math.PI / 180 ) ) * starRadius ),
                yMod2 = (int) ( Math.sin( 36 * ( Math.PI / 180 ) ) * starRadius );
        g.setColor( Color.white );
        Polygon star = new Polygon();
        star.addPoint( currX, currY );
        currX += xMod;
        currY += yMod;
        star.addPoint( currX, currY );
        currX += starRadius;
        star.addPoint( currX, currY );
        currX -= yMod;
        currY += xMod;
        star.addPoint( currX, currY );
        currX += xMod;
        currY += yMod;
        star.addPoint( currX, currY );
        currX -= xMod2;
        currY -= yMod2;
        star.addPoint( currX, currY );
        currX -= xMod2;
        currY += yMod2;
        star.addPoint( currX, currY );
        currX += xMod;
        currY -= yMod;
        star.addPoint( currX, currY );
        currX -= yMod;
        currY -= xMod;
        star.addPoint( currX, currY );
        currX += starRadius;
        star.addPoint( currX, currY );
        g.fillPolygon( star );
        g.setColor( Color.black );
        g.drawPolygon( star );
    }

    public static int getRadius() {
        return radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
