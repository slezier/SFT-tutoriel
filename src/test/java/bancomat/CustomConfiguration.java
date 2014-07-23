package bancomat;

import sft.DefaultConfiguration;
import sft.plugins.sequenceDiagramPlugin.HtmlSequenceDiagram;
import sft.plugins.sequenceDiagramPlugin.SequenceDiagram;

public class CustomConfiguration extends DefaultConfiguration {
    public CustomConfiguration() {
        getReport().addDecorator(SequenceDiagram.class, HtmlSequenceDiagram.class);
    }
}
