package org.koitharu.kotatsu.parsers.stub

import org.koitharu.kotatsu.parsers.Broken
import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaSourceParser
import org.koitharu.kotatsu.parsers.config.ConfigKey
import org.koitharu.kotatsu.parsers.core.SinglePageMangaParser
import org.koitharu.kotatsu.parsers.model.Manga
import org.koitharu.kotatsu.parsers.model.MangaChapter
import org.koitharu.kotatsu.parsers.model.MangaListFilter
import org.koitharu.kotatsu.parsers.model.MangaListFilterCapabilities
import org.koitharu.kotatsu.parsers.model.MangaListFilterOptions
import org.koitharu.kotatsu.parsers.model.MangaPage
import org.koitharu.kotatsu.parsers.model.MangaParserSource
import org.koitharu.kotatsu.parsers.model.SortOrder

/**
 * The only [MangaSourceParser] in this stripped build. It exists solely so the KSP codegen still
 * emits the [MangaParserSource] enum + factory that the rest of the parser infrastructure references
 * (the processor skips generation entirely when there are zero annotated parsers). It parses nothing
 * and is flagged [Broken] so the host app filters it out of every source list — real sources are
 * provided at runtime by the data-driven catalogue, not compiled in.
 */
@Broken("Placeholder — real sources are provided at runtime by the data-driven catalogue")
@MangaSourceParser("STUB", "Stub")
internal class StubParser(
	context: MangaLoaderContext,
) : SinglePageMangaParser(context, MangaParserSource.STUB) {

	override val configKeyDomain = ConfigKey.Domain("example.org")

	override val availableSortOrders: Set<SortOrder> = setOf(SortOrder.NEWEST)

	override val filterCapabilities: MangaListFilterCapabilities = MangaListFilterCapabilities()

	override suspend fun getList(order: SortOrder, filter: MangaListFilter): List<Manga> = emptyList()

	override suspend fun getDetails(manga: Manga): Manga = manga

	override suspend fun getPages(chapter: MangaChapter): List<MangaPage> = emptyList()

	override suspend fun getFilterOptions(): MangaListFilterOptions = MangaListFilterOptions()
}
