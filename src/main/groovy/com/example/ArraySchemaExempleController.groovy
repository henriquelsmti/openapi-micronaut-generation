package com.example

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.server.types.files.StreamedFile
import io.reactivex.Observable
import io.reactivex.Single
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.responses.ApiResponse

@Controller('/arrayschema')
class ArraySchemaExempleController {

	@ApiResponse(
			content = @Content(
					array = @ArraySchema(
							schema = @Schema(implementation = Bacon)
					)
			)
	)
	@RequestBody(
			content = @Content(
					array = @ArraySchema(
							schema = @Schema(implementation = Bacon)
					)
			)
	)
	@Post('/single')
	Single<List<Bacon>> singleExemple(@Body Single<List<Bacon>> single) {
		return single.map((it) -> it + [new Bacon(name: 'banana')])
	}

	@ApiResponse(
			content = @Content(
					array = @ArraySchema(
							schema = @Schema(implementation = Bacon)
					)
			)
	)
	@RequestBody(
			content = @Content(
					array = @ArraySchema(
							schema = @Schema(implementation = Bacon)
					)
			)
	)
	@Post('/observable')
	Observable<List<String>> observableExemple(@Body Observable<List<String>> observable) {
		return observable.map((it) -> it + [new Bacon(name: 'banana')])
	}

	@ApiResponse(
			content = @Content(
					array = @ArraySchema(
							schema = @Schema(implementation = Bacon)
					)
			)
	)
	@Get('/httpResponseExemple')
	HttpResponse<List<Bacon>> httpResponseExemple() {
		return HttpResponse.ok([new Bacon(name: 'banana')])
	}

	@ApiResponse(
			content = @Content(
					array = @ArraySchema(
							schema = @Schema(implementation = Bacon)
					)
			)
	)
	@Get('/streamedFileExemple')
	StreamedFile streamedFileExemple(){
		InputStream inputStream = new ByteArrayInputStream('[{"name":"banana"}]'.bytes)
		new StreamedFile(inputStream, MediaType.APPLICATION_JSON_TYPE)
	}
}
