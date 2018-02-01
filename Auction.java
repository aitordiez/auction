import java.util.ArrayList;
import java.util.Iterator;
/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael KÃ¶lling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            boolean successful = selectedLot.bidFor(new Bid(bidder, value));
            if(successful) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.
                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    selectedLot.getHighestBid().getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        if((lotNumber >= 1) && (lotNumber < nextLotNumber)) {
            // The number seems to be reasonable.
            Lot selectedLot = lots.get(lotNumber - 1);
            // Include a confidence check to be sure we have the
            // right lot.
            if(selectedLot.getNumber() != lotNumber) {
                System.out.println("Internal error: Lot number " +
                    selectedLot.getNumber() +
                    " was returned instead of " +
                    lotNumber);
                // Don't return an invalid lot.
                selectedLot = null;
            }
            return selectedLot;
        }
        else {
            System.out.println("Lot number: " + lotNumber +
                " does not exist.");
            return null;
        }
    }

    /**
     * Este método debe iterar a través de la colección de lotes e imprimir los 
     * detalles de todos los lotes. Cualquier lote que tenga al menos una puja
     * se considerará vendido, de modo que lo que estamos buscando son objetos 
     * Lot cuyo campo highestBid no sea null. Utilice una variable local dentro
     * del bucle para almacenar el valor devuelto por las llamadas al método 
     * getHighestBid, y luego compruebe si dicha variable tiene el valor null. 
     * Para lotes que tengan asignada una puja, los detalles deben incluir el 
     * nombre de la persona que ha hecho la puja y el valor de esa puja mas alta
     * Para los lotes por los que nadie haya pujado, imprima un mendaje que lo 
     * indique.
     */
    public void close(){
        for(Lot subasta : lots){
            System.out.println(subasta.toString());
            Bid pujaMasAlta = subasta.getHighestBid();
            if(pujaMasAlta != null){
                System.out.println("El nombre de la persona es: " + pujaMasAlta.getBidder().getName());
                System.out.println("El valor de la puja es: " + pujaMasAlta.getValue());
            }
        }
    }

    /**
     * Este método debe iterar a través del campo lots, almacenando los lotes 
     * no vendidos en una variable local ArrayList. Lo que estamos buscando son 
     * los objetos Lot cuyo campo highestBid sea null. Al final del método, 
     * devuelva la lista de los lotes no vendidos.
     */
    public ArrayList<Lot> getUnsold(){
        ArrayList<Lot>copiaLots = new ArrayList<Lot>();
        for(Lot elementos : lots){
            Bid elementosNull = elementos.getHighestBid();
            if(elementosNull == null){
                copiaLots.add(elementos);
            }
        }
        return copiaLots;
    }
    
    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLots(int number)
    {
        Lot result = null;      
        Iterator<Lot> i = lots.iterator();
        while(i.hasNext())
        {
                Lot temp = i.next();
                if (temp.getNumber() == number) {
                   return temp;
                }
        }
        return null;
    }   
}

