import java.util.Comparator;

public class SortByCenna implements Comparator<Auto> {

	@Override
	public int compare(Auto arg0, Auto arg1) {
		return Math.round(arg0.getCena()-arg1.getCena());
	}

}
