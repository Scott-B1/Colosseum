
package uk.ac.qub.eeecs.game.Colosseum.Regions;

import uk.ac.qub.eeecs.game.Colosseum.Card;

/**
 * Created by Kyle Corrigan
 *
 * Class used to define the hand region of the game board, within which, cards are drawn to
 * @author Kyle Corrigan
 */

public class HandRegion extends GameRegion {

    // /////////////////////////////////////////////////////////////////////////
    // Constructors
    // /////////////////////////////////////////////////////////////////////////

    public HandRegion (float xLeftEdge,float xRightEdge, float yTopEdge, float yBottomEdge){
        super(xLeftEdge,xRightEdge,yTopEdge,yBottomEdge);

        setMaxNumCardsInRegion(8);

    }

    // /////////////////////////////////////////////////////////////////////////
    // Methods
    // /////////////////////////////////////////////////////////////////////////

    /**
     * If the board region is not full, update the card position, add card to that region's card array
     * called when drawing from deck.
     *
     * @param card Card which is having its position updated
     */

    public void addCard (Card card) {

        if (!isRegionFull()) {

            getCardsInRegion().add(card);
            setCardPosition(card);
            card.setCurrentRegion("Hand");

        }

    }

    /**
     * Update function for the hand. If a card is present within a region when a held card is dropped
     * or, a card is dropped into a region, adds the card to the selected region. Currently uses
     * addCard method to do this
     *
     * @param card Card being updated
     */

    public void update(Card card){

        // If a card is moved outside of the hand region by any means, remove it from array list
        // Note: If card is dropped in a different but valid region, card's current region is changed
        // that of the related state, allowing for a different related method to handle it.
        if (!isInRegion(card) && card.getCurrentRegion()=="Hand"){

            removeCard(card);
            // If card is dropped to an invalid location, add the card back to the hand
            if(card.getCardDropped()){
                addCard(card);
            }

        }

    }

}