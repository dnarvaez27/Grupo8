package controllers;

import actions.Roles;
import actions.RolesAllowed;
import com.fasterxml.jackson.databind.JsonNode;
import models.main.Sensor;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

/**
 * @author dnarvaez27
 */
public class SensorController extends Controller
{
	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result create( Long idMicrocontrolador )
	{
		JsonNode json = request( ).body( ).asJson( );
		Sensor sensor = Sensor.bind( json );
		models.main.Microcontrolador microcontrolador = new models.main.Microcontrolador();
		microcontrolador.setId( idMicrocontrolador );
		sensor.setMicrocontrolador( microcontrolador );
		sensor.save( );
		return ok( Json.toJson( sensor ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieveAll( Long idMicrocontrolador )
	{
		List<Sensor> sensor = Sensor.find.where( ).eq( "microcontrolador.id", idMicrocontrolador ).findList( );
		return ok( Json.toJson( sensor ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result retrieve( Long id )
	{
		Sensor sensor = Sensor.find.byId( id );
		return ok( Json.toJson( sensor ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	@BodyParser.Of( BodyParser.Json.class )
	public Result update( Long id )
	{
		JsonNode json = request( ).body( ).asJson( );
		Sensor sensor = Sensor.bind( json );
		sensor.setId( id );
		sensor.update( );
		return ok( Json.toJson( sensor ) );
	}

	@RolesAllowed( { Roles.ADMIN, Roles.SUPERVISOR } )
	public Result delete( Long id )
	{
		Sensor sensor = Sensor.find.byId( id );
		sensor.delete( );
		return ok( Json.toJson( sensor ) );
	}
}
