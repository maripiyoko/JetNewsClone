package app.chestnuts.jetnewsclone.model

data class Post(
    val id: String,
    val title: String,
    val subtitle: String? = null,
    val url: String,
    val publication: Publication? = null,
    val metadata: Metadata,
    val paragraphs: List<Paragraph>,
    val thumbnailImageUrl: String? = null,
    val imageUrl: String? = null,
)

data class Metadata(
    val author: PostAuthor,
    val date: String,
    val readTimeMinutes: Int,
)

data class Publication(
    val name: String,
    val logoUrl: String,
) {
    companion object {
        fun empty(): Publication = Publication(name = "", logoUrl = "")
    }
}

data class PostAuthor(
    val name: String,
    val url: String? = null,
)

data class Paragraph(
    val type: ParagraphType,
    val text: String,
    val markups: List<Markup> = emptyList(),
)

data class Markup(
    val type: MarkupType,
    val start: Int,
    val end: Int,
    val href: String? = null,
)

enum class MarkupType {
    Link,
    Code,
    Italic,
    Bold,
}

enum class ParagraphType {
    Title,
    Caption,
    Header,
    Subhead,
    Text,
    CodeBlock,
    Quote,
    Bullet,
}