package components;

import com.google.inject.Inject;
import pages.AbsBasePage;
import support.GuiceScoped;

public abstract class AbsComponent<T> extends AbsBasePage {
	@Inject
	public AbsComponent(GuiceScoped guiceScoped) {
		super(guiceScoped);
	}
}
