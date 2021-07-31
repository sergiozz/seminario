package ubicando

import grails.core.GrailsApplication
import grails.plugins.*
import static Constants.*

class ApplicationController implements PluginManagerAware {

    GrailsApplication grailsApplication
    GrailsPluginManager pluginManager

    def index() {
        [grailsApplication: grailsApplication, pluginManager: pluginManager]
    }

    def getAllActions() {
        respond Constants.todasLasAcciones
    }
}
