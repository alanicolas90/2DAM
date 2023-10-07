package domain.modelo.berry;


import lombok.Getter;

import java.util.List;

@Getter
public class BerryResponse{
	private int id;
	private int smoothness;
	private int size;
	private int growth_time;
	private int natural_gift_power;
	private int max_harvest;
	private String name;

	private List<FlavorsItem> flavors;
	private NaturalGiftType natural_gift_type;
	private Item item;
}