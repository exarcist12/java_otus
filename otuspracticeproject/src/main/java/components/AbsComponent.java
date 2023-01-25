package components;

import com.google.inject.Inject;
import data.pages.AbsBasePage;
import support.GuiceScoped;

public abstract class AbsComponent<T> extends AbsBasePage {
	@Inject
	public AbsComponent(GuiceScoped guiceScoped) {
		super(guiceScoped);
	}
}
