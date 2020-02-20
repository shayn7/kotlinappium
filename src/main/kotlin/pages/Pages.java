package pages;

import mobile_platforms.AbstractPlatform;

public enum Pages {

    GmailHomePage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new GmailHomePage(abstractPlatform);
        }
    },
    GmailSentPage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new GmailSentPage(abstractPlatform);
        }
    },
    GmailComposePage() {
        @Override
        public AbstractPage getPage(AbstractPlatform abstractPlatform) {
            return new GmailComposePage(abstractPlatform);
        }
    };

    public abstract AbstractPage getPage(AbstractPlatform abstractPlatform);
}
