package domain.modelo.jokes;

import lombok.Data;

@Data
public class Flags{
	private final boolean sexist;
	private boolean explicit;
	private boolean religious;
	private boolean nsfw;
	private boolean political;
	private boolean racist;
}
